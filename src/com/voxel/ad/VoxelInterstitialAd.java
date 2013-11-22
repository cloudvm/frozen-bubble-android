package com.voxel.ad;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.voxel.frozenbubble.R;
import com.voxel.sdk.VoxelAppConfig;
import com.voxel.sdk.VoxelSDK;
import com.voxel.sdk.ad.VoxelAppDialog;

@TargetApi(5)
public class VoxelInterstitialAd extends Dialog {

	private static final String TAG = "PreInterstitialDialog";

	private Context mContext = null;
	private Bitmap mBitmap;

	public VoxelInterstitialAd(Context context) {
		super(context);
		mContext = context;
	}

	public void setup(int imageId, String text) {
		setContentView(R.layout.voxel_interstitial);

		ImageView img = (ImageView) findViewById(R.id.pre_dialog_image);
		mBitmap = BitmapFactory
				.decodeResource(mContext.getResources(), imageId);
		img.setImageBitmap(mBitmap);

		Button playButton = (Button) findViewById(R.id.pre_dialog_button);
		playButton.setVisibility(View.VISIBLE);
		playButton.setBackgroundColor(Color.TRANSPARENT);

		playButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "Button clicked");
				launchGameDemo();
				dismiss();
			}
		});
	}

	@Override
	public void dismiss() {
		super.dismiss();
		mBitmap.recycle();
	}

	private void launchGameDemo() {
		if (VoxelSDK.canRunSession()) {
			VoxelAppConfig config = new VoxelAppConfig();
			// config.setAppId("com.rovio.angrybirds");
			config.setCampaignId(7);
			VoxelAppDialog vad = VoxelSDK.createSessionDialog(mContext, config);
			vad.show();
		}
	}
}
