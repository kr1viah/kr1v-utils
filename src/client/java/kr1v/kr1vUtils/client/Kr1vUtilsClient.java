package kr1v.kr1vUtils.client;

import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import kr1v.malilibApi.MalilibApi;
import kr1v.malilibApi.annotation.MainClass;
import net.fabricmc.api.ClientModInitializer;

import static kr1v.kr1vUtils.client.Kr1vUtilsClient.MOD_ID;

@MainClass(MOD_ID)
public class Kr1vUtilsClient implements ClientModInitializer {
	public static final String MOD_ID = "kr1v-utils";

	@Override
	public void onInitializeClient() {
		ClassUtils.touch(Annotations.class);
        MalilibApi.registerMod(MOD_ID);
	}
}
