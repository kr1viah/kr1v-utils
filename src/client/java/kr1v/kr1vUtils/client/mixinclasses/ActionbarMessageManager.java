package kr1v.kr1vUtils.client.mixinclasses;

import kr1v.kr1vUtils.client.config.configs.Misc;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ActionbarMessageManager {
	public static final List<Text> serverMessages = new ArrayList<>();

	public static boolean displayOnTop(Text text) {
		if (serverMessages.remove(text)) {
			return !Misc.CLIENT_ON_TOP.getBooleanValue();
		}  else {
			return Misc.CLIENT_ON_TOP.getBooleanValue();
		}
	}
}
