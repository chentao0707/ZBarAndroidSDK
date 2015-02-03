package com.obsessive.zbar;

import java.io.IOException;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;

/**
 * 
 * 邮箱: 1076559197@qq.com
 * 
 * 作者: 强迫症患者
 * 
 */
public final class CameraManager {

	private static final String TAG = CameraManager.class.getSimpleName();

	private final CameraConfigurationManager configManager;

	private Camera camera;
	private boolean initialized;

	public CameraManager(Context context) {
		this.configManager = new CameraConfigurationManager(context);
	}

	/**
	 * Opens the camera driver and initializes the hardware parameters.
	 * 
	 * @param holder
	 *            The surface object which the camera will draw preview frames
	 *            into.
	 * @throws IOException
	 *             Indicates the camera driver failed to open.
	 */
	public synchronized void openDriver() throws IOException {
		Camera theCamera = camera;
		if (theCamera == null) {
			theCamera = Camera.open();
			if (theCamera == null) {
				throw new IOException();
			}
			camera = theCamera;
		}

		if (!initialized) {
			initialized = true;
			configManager.initFromCameraParameters(theCamera);
		}

		Camera.Parameters parameters = theCamera.getParameters();
		String parametersFlattened = parameters == null ? null : parameters
				.flatten(); // Save
							// these,
							// temporarily
		try {
			configManager.setDesiredCameraParameters(theCamera, false);
		} catch (RuntimeException re) {
			// Driver failed
			Log.w(TAG,
					"Camera rejected parameters. Setting only minimal safe-mode parameters");
			Log.i(TAG, "Resetting to saved camera params: "
					+ parametersFlattened);
			// Reset:
			if (parametersFlattened != null) {
				parameters = theCamera.getParameters();
				parameters.unflatten(parametersFlattened);
				try {
					theCamera.setParameters(parameters);
					configManager.setDesiredCameraParameters(theCamera, true);
				} catch (RuntimeException re2) {
					// Well, darn. Give up
					Log.w(TAG,
							"Camera rejected even safe-mode parameters! No configuration");
				}
			}
		}

	}

	public synchronized boolean isOpen() {
		return camera != null;
	}

	public Camera getCamera() {
		return camera;
	}

	/**
	 * Closes the camera driver if still in use.
	 */
	public synchronized void closeDriver() {
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

	/**
	 * 获取相机分辨率
	 * 
	 * @return
	 */
	public Point getCameraResolution() {
		return configManager.getCameraResolution();
	}
}
