package kr1v.kr1vUtils.client;

import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import kr1v.malilibApi.ConfigHandler;
import kr1v.malilibApi.InputHandler;
import kr1v.malilibApi.MalilibApi;
import net.fabricmc.api.ClientModInitializer;

public class Kr1vUtilsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClassUtils.touch(Annotations.class);
		MalilibApi.registerMod("kr1v-utils", "kr1v utils", new ConfigHandler("kr1v-utils"), new InputHandler("kr1v-utils"));
	}
}
