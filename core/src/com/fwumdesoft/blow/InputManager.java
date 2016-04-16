package com.fwumdesoft.blow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class InputManager extends Actor {

	int tickCooldown;
	GameScreen screen;
	final int maxTick = 30;
	
	public InputManager(GameScreen screen, final Reflector... reflectors) {
		this.screen = screen;
		ControllerListener controllerListen = new ControllerAdapter() {

			@Override
			public boolean buttonDown(Controller controller, int buttonCode) {
				if(tickCooldown > 0) return false;
				tickCooldown = maxTick;
				switch(buttonCode) {
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
				}
				return true;
			}

			@Override
			public boolean povMoved(Controller controller, int povCode, PovDirection value) {
				if(tickCooldown > 0) return false;
				tickCooldown = maxTick;
				switch(value) {
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
					break;
				}
				return true;
			}

		};

		InputAdapter inListen = new InputAdapter() {
			public boolean keyDown(int keycode) {
				System.out.println(keycode);
				return false;
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
	
	@Override
	public void act(float delta) {
		if(tickCooldown > 0)
			tickCooldown--;
	}
	
	private void trigger(Reflector reflector) {
		if(screen.isAttacking) {
			reflector.launchMissile();
		} else {
			reflector.reflectMissiles();
		}
	}
}
