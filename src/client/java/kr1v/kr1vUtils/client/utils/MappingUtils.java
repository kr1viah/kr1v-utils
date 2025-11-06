package kr1v.kr1vUtils.client.utils;

import kr1v.kr1vUtils.client.utils.annotation.Touch;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.mappingio.MappingReader;
import net.fabricmc.mappingio.tree.MappingTree;
import net.fabricmc.mappingio.tree.MemoryMappingTree;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

@Touch
public class MappingUtils {
	private static final Map<String, String> cachedClasses = new HashMap<>();
	private static final Map<String, String> cachedFields = new HashMap<>();
	private static final Map<String, String> cachedMethods = new HashMap<>();
	private static final MemoryMappingTree tree = new MemoryMappingTree();
	private static final Path mappingsPath = MinecraftClient.getInstance().runDirectory.toPath().resolve(".tiny").resolve("yarn-" + MinecraftVersion.CURRENT.getName() + "+build.1-tiny");


	@SuppressWarnings("DataFlowIssue")
	public static String intermediaryToYarn(Class<?> intermediaryClass) {
		String intermediaryName = intermediaryClass.getName();
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) return intermediaryName; // already yarn

		String named = cachedClasses.getOrDefault(intermediaryName, null);
		if (named != null) return named;
		for (MappingTree.ClassMapping c : tree.getClasses()) {
			String inter = c.getDstName(0).replace("/", ".");
			if (Objects.equals(inter, intermediaryName)) {
				named = c.getDstName(1).replace("/", ".");
				cachedClasses.put(inter, named);
				break;
			}
		}
		if (named == null) named = intermediaryName;
		return named;
	}

	public static String intermediaryToYarnSimple(Class<?> intermediaryClass) {
		String yarnName = intermediaryToYarn(intermediaryClass);
		return yarnName.substring(yarnName.lastIndexOf(".") + 1);
	}

	@SuppressWarnings("DataFlowIssue")
	public static String intermediaryToYarn(Field intermediaryField) {
		String ownerInter = intermediaryField.getDeclaringClass().getName();
		String key = ownerInter + "#" + intermediaryField.getName() + ":" + descriptorForType(intermediaryField.getType());
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return ownerInter + "." + intermediaryField.getName();
		}

		String cached = cachedFields.get(key);
		if (cached != null) return cached;

		String result = ownerInter + "." + intermediaryField.getName(); // fallback

		for (MappingTree.ClassMapping c : tree.getClasses()) {
			String inter = c.getDstName(0).replace("/", ".");
			if (Objects.equals(inter, ownerInter)) {
				try {
					MappingTree.FieldMapping fm = c.getField(intermediaryField.getName(), descriptorForType(intermediaryField.getType()));
					if (fm != null && fm.getDstName(1) != null) {
						String ownerYarn = c.getDstName(1).replace("/", ".");
						result = ownerYarn + "." + fm.getDstName(1);
						break;
					}
				} catch (UnsupportedOperationException | NoSuchMethodError ignored) {
				}

				for (MappingTree.FieldMapping fm : c.getFields()) {
					String interFieldName = fm.getDstName(0);
					if (Objects.equals(interFieldName, intermediaryField.getName())) {
						String yarnFieldName = fm.getDstName(1) != null ? fm.getDstName(1) : intermediaryField.getName();
						String ownerYarn = c.getDstName(1).replace("/", ".");
						result = ownerYarn + "." + yarnFieldName;
						break;
					}
				}

				break;
			}
		}

		cachedFields.put(key, result);
		return result;
	}

	public static String intermediaryToYarnSimple(Field intermediaryField) {
		String full = intermediaryToYarn(intermediaryField);
		return full.substring(full.lastIndexOf(".") + 1);
	}

	@SuppressWarnings("DataFlowIssue")
	public static String intermediaryToYarn(Method intermediaryMethod) {
		String ownerInter = intermediaryMethod.getDeclaringClass().getName();
		String desc = descriptorForMethod(intermediaryMethod);
		String key = ownerInter + "#" + intermediaryMethod.getName() + ":" + desc;

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return ownerInter + "." + intermediaryMethod.getName();
		}

		String cached = cachedMethods.get(key);
		if (cached != null) return cached;

		String result = ownerInter + "." + intermediaryMethod.getName(); // fallback

		for (MappingTree.ClassMapping c : tree.getClasses()) {
			String inter = c.getDstName(0).replace("/", ".");
			if (Objects.equals(inter, ownerInter)) {
				try {
					MappingTree.MethodMapping mm = c.getMethod(intermediaryMethod.getName(), desc);
					if (mm != null && mm.getDstName(1) != null) {
						String ownerYarn = c.getDstName(1).replace("/", ".");
						result = ownerYarn + "." + mm.getDstName(1);
						break;
					}
				} catch (UnsupportedOperationException | NoSuchMethodError ignored) {
				}

				for (MappingTree.MethodMapping mm : c.getMethods()) {
					String interMethodName = mm.getDstName(0);
					if (Objects.equals(interMethodName, intermediaryMethod.getName())) {
						try {
							String mappedDesc = mm.getDstDesc(0);
							if (mappedDesc != null && Objects.equals(mappedDesc, desc)) {
								String yarnMethodName = mm.getDstName(1) != null ? mm.getDstName(1) : intermediaryMethod.getName();
								String ownerYarn = c.getDstName(1).replace("/", ".");
								result = ownerYarn + "." + yarnMethodName;
								break;
							}
						} catch (UnsupportedOperationException | NoSuchMethodError e) {
							String yarnMethodName = mm.getDstName(1) != null ? mm.getDstName(1) : intermediaryMethod.getName();
							String ownerYarn = c.getDstName(1).replace("/", ".");
							result = ownerYarn + "." + yarnMethodName;
							break;
						}
					}
				}

				break;
			}
		}

		cachedMethods.put(key, result);
		return result;
	}

	public static String intermediaryToYarnSimple(Method intermediaryMethod) {
		String full = intermediaryToYarn(intermediaryMethod);
		return full.substring(full.lastIndexOf(".") + 1);
	}

	private static String descriptorForType(Class<?> type) {
		if (type.isPrimitive()) {
			if (type == void.class) return "V";
			if (type == boolean.class) return "Z";
			if (type == byte.class) return "B";
			if (type == char.class) return "C";
			if (type == short.class) return "S";
			if (type == int.class) return "I";
			if (type == long.class) return "J";
			if (type == float.class) return "F";
			if (type == double.class) return "D";
		}

		if (type.isArray()) {
			return "[" + descriptorForType(type.getComponentType());
		}

		return "L" + type.getName().replace(".", "/") + ";";
	}

	private static String descriptorForMethod(Method m) {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		for (Class<?> p : m.getParameterTypes()) {
			sb.append(descriptorForType(p));
		}
		sb.append(')');
		sb.append(descriptorForType(m.getReturnType()));
		return sb.toString();
	}


	static {
		try {
			if (!mappingsPath.toFile().exists()) {
				String version = MinecraftVersion.CURRENT.getName();
				String url = "https://maven.fabricmc.net/net/fabricmc/yarn/" + version + "%2Bbuild.1/yarn-" + version + "%2Bbuild.1-tiny.gz";

				var gzPath = mappingsPath.resolveSibling("temp.gz");
				InputStream in = URI.create(url).toURL().openStream();

				if (!Files.exists(gzPath)) {
					Files.createDirectories(gzPath.getParent());
					Files.createFile(gzPath);
				}

				Files.copy(in, gzPath, StandardCopyOption.REPLACE_EXISTING);
				GZIPInputStream gis = new GZIPInputStream(new FileInputStream(gzPath.toFile()));
				try (FileOutputStream fos = new FileOutputStream(mappingsPath.toFile())) {

					byte[] buffer = new byte[8192];
					int len;
					while ((len = gis.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}
				}

				Files.delete(gzPath);
			}

			MappingReader.read(mappingsPath, tree);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
