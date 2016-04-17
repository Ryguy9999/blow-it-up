package com.fwumdesoft.blow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.Array;

public class InputManager {

	GameScreen screen;

	public InputManager(GameScreen screen, final Reflector... reflectors) {
		this.screen = screen;
		ControllerListener controllerListen = new ControllerAdapter() {

			@Override
			public boolean buttonDown(Controller controller, int buttonCode) {
				switch (buttonCode) {
				case 0:
					trigger(reflectors[1]);
					break;
				case 1:
					trigger(reflectors[7]);
					break;
				case 2:
					trigger(reflectors[5]);
					break;
				case 3:
					trigger(reflectors[3]);
					break;
				default:
					return false;
				}
				return true;
			}

			@Override
			public boolean povMoved(Controller controller, int povCode, PovDirection value) {
				switch (value) {
				case east:
					trigger(reflectors[0]);
					break;
				case north:
					trigger(reflectors[2]);
					break;
				case south:
					trigger(reflectors[6]);
					break;
				case west:
					trigger(reflectors[4]);
					break;
				default:
					return false;
				}
				return true;
			}

		};

		InputAdapter inListen = new InputAdapter() {
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case 32:
				case 150:
					trigger(reflectors[0]);
					break;
				case 33:
				case 153:
					trigger(reflectors[1]);
					break;
				case 51:
				case 152:
					trigger(reflectors[2]);
					break;
				case 45:
				case 151:
					trigger(reflectors[3]);
					break;
				case 29:
				case 148:
					trigger(reflectors[4]);
					break;
				case 54:
				case 145:
					trigger(reflectors[5]);
					break;
				case 52:
				case 146:
					trigger(reflectors[6]);
					break;
				case 31:
				case 147:
					trigger(reflectors[7]);
					break;
				default:
					System.out.println(keycode);
					return false;
				}
				return true;
			}
		};
		Array<Controller> controllers = Controllers.getControllers();
		System.out.println(controllers);
		if (controllers.size != 0) {
			Controller controller = Controllers.getControllers().first();
			controller.addListener(controllerListen);
		}
		Gdx.input.setInputProcessor(inListen);
	}

	private void trigger(Reflector reflector) {
		if (screen.isAttacking) {
			reflector.launchMissile();
		} else {
			reflector.reflectMissiles();
		}
	}
}
