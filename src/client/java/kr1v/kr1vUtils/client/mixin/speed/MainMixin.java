package kr1v.kr1vUtils.client.mixin.speed;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.util.JsonUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.config.Misc;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.lib.tinyremapper.extension.mixin.common.data.Pair;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.List;

@Mixin(Main.class)
public class MainMixin {
	@Inject(method = "<clinit>", at = @At("HEAD"))
	private static void earlyConfigReader(CallbackInfo ci) {
		List<Pair<String, IConfigBase>> toRead = List.of(
			Pair.of("Misc", Misc.FAST_MAIN_MENU)
		);

		File configFile = FabricLoader.getInstance().getConfigDir().resolve(Kr1vUtilsClient.MOD_ID + ".json").toFile();
		if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
			JsonElement element = JsonUtils.parseJsonFile(configFile);
			if (element != null && element.isJsonObject()) {
				JsonObject root = element.getAsJsonObject();
				if (root.has("configs") && root.get("configs").isJsonObject()) {
					JsonObject configs = root.get("configs").getAsJsonObject();
					for (Pair<String, IConfigBase> config : toRead) {
						config.second().setValueFromJsonElement(configs.get(config.first()).getAsJsonObject().get(config.second().getName()));
					}
//					Misc.FAST_MAIN_MENU.setValueFromJsonElement(configs.get("Misc").getAsJsonObject().get("Fast main menu"));
				}
			}
		}
	}
}
