package kr1v.kr1vUtils.client;

import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.ClassUtils;
import net.fabricmc.api.ClientModInitializer;

public class Kr1vUtilsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClassUtils.touch(Annotations.class);
	}
}
