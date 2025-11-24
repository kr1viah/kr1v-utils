package kr1v.kr1vUtils.client.utils.malilib;


import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

@SuppressWarnings("unused")
public class KeybindSetting {
	public static KeybindSettings create(KeybindSettings.Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive, boolean exclusive, boolean cancel, boolean allowEmpty) {
		return KeybindSettings.create(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty);
	}

	public static KeybindSettings ofInGame() {
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

	public static KeybindSettings ofGui() {
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

	public static KeybindSettings ofAny() {
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

	public static KeybindSettings ofPress() {
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

	public static KeybindSettings ofRelease() {
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

	public static KeybindSettings ofBoth() {
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

	public static KeybindSettings ofAllowExtraKeys() {
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

	public static KeybindSettings ofDisallowExtraKeys() {
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

	public static KeybindSettings ofOrderSensitive() {
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

	public static KeybindSettings ofNotOrderSensitive() {
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

	public static KeybindSettings ofExclusive() {
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

	public static KeybindSettings ofNonExclusive() {
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

	public static KeybindSettings ofCancel() {
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

	public static KeybindSettings ofDontCancel() {
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

	public static KeybindSettings ofAllowEmpty() {
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

	public static KeybindSettings ofDisallowEmpty() {
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
}
