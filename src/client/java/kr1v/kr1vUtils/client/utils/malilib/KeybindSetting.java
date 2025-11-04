package kr1v.kr1vUtils.client.utils.malilib;


import dev.lukebemish.opensesame.annotations.extend.Constructor;
import dev.lukebemish.opensesame.annotations.extend.Extend;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

@SuppressWarnings("unused")
@Extend(targetClass = KeybindSettings.class, unsafe = true)
public interface KeybindSetting {
	@Constructor
	static KeybindSetting create(KeybindSettings.Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive, boolean exclusive, boolean cancel, boolean allowEmpty) {
		throw new AssertionError();
	}

	static KeybindSetting ofInGame() {
		return create(
			KeybindSettings.Context.INGAME,
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofGui() {
		return create(
			KeybindSettings.Context.GUI,
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofAny() {
		return create(
			KeybindSettings.Context.ANY,
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofPress() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeyAction.PRESS,
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofRelease() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeyAction.RELEASE,
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofBoth() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeyAction.BOTH,
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofAllowExtraKeys() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			true,
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofDisallowExtraKeys() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			false,
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofOrderSensitive() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			true,
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofNotOrderSensitive() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			false,
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofExclusive() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			true,
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofNonExclusive() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			false,
			KeybindSettings.DEFAULT.shouldCancel(),
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofCancel() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			true,
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofDontCancel() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			false,
			KeybindSettings.DEFAULT.getAllowEmpty()
		);
	}

	static KeybindSetting ofAllowEmpty() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			true
		);
	}

	static KeybindSetting ofDisallowEmpty() {
		return create(
			KeybindSettings.DEFAULT.getContext(),
			KeybindSettings.DEFAULT.getActivateOn(),
			KeybindSettings.DEFAULT.getAllowExtraKeys(),
			KeybindSettings.DEFAULT.isOrderSensitive(),
			KeybindSettings.DEFAULT.isExclusive(),
			KeybindSettings.DEFAULT.shouldCancel(),
			false
		);
	}

	default KeybindSetting inGame() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			KeybindSettings.Context.INGAME,
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting gui() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			KeybindSettings.Context.GUI,
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting any() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			KeybindSettings.Context.ANY,
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting onPress() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			KeyAction.PRESS,
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting onRelease() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			KeyAction.RELEASE,
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting onBoth() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			KeyAction.BOTH,
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting allowExtraKeys() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			true,
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting disallowExtraKeys() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			false,
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting orderSensitive() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			true,
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting notOrderSensitive() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			false,
			thiz.isExclusive(),
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting exclusive() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			true,
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting nonExclusive() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			false,
			thiz.shouldCancel(),
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting cancel() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			true,
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting dontCancel() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			false,
			thiz.getAllowEmpty()
		);
	}

	default KeybindSetting allowEmpty() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			true
		);
	}

	default KeybindSetting disallowEmpty() {
		KeybindSettings thiz = (KeybindSettings) this;
		return create(
			thiz.getContext(),
			thiz.getActivateOn(),
			thiz.getAllowExtraKeys(),
			thiz.isOrderSensitive(),
			thiz.isExclusive(),
			thiz.shouldCancel(),
			false
		);
	}
}
