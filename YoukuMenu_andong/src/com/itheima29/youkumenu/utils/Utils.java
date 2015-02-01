package com.itheima29.youkumenu.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;

public class Utils {
	
	public static int runAnimationCount = 0;	// 当前运行动画的数量

	/**
	 * 出去的动画
	 * @param view
	 */
	public static void startOutAnimation(ViewGroup viewGroup, long startOffset) {
		// 需要把viewgroup中所有的子布局置为不可用
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setEnabled(false);		// 当前不可以被点击
		}
		
		
		RotateAnimation ra = new RotateAnimation(
				0f, -180f,	// 开始和结束的角度 
				Animation.RELATIVE_TO_SELF, 0.5f,	// x轴的参照物和值是: 自己宽度的一半 
				Animation.RELATIVE_TO_SELF, 1f);	// y走的参照物和值是: 自己的高度
		ra.setDuration(500);
		ra.setFillAfter(true);	// 设置动画停止在动画结束的状态下
		ra.setStartOffset(startOffset);	// 设置动画开始执行的延时时间
		ra.setAnimationListener(new MyAnimationListener());
		viewGroup.startAnimation(ra);		// 执行动画
	}

	/**
	 * 进来的动画
	 * @param view
	 */
	public static void startInAnimation(ViewGroup viewGroup, long startOffset) {
		// 需要把viewgroup中所有的子布局置为可用
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setEnabled(true);		// 当前可以被点击
		}
		
		RotateAnimation ra = new RotateAnimation(
				-180f, 0f,	// 开始和结束的角度 
				Animation.RELATIVE_TO_SELF, 0.5f,	// x轴的参照物和值是: 自己宽度的一半 
				Animation.RELATIVE_TO_SELF, 1f);	// y走的参照物和值是: 自己的高度
		ra.setDuration(500);
		ra.setFillAfter(true);	// 设置动画停止在动画结束的状态下
		ra.setStartOffset(startOffset);
		ra.setAnimationListener(new MyAnimationListener());
		viewGroup.startAnimation(ra);		// 执行动画
	}
	
	static class MyAnimationListener implements AnimationListener {

		/**
		 * 当动画开始执行时回调
		 */
		@Override
		public void onAnimationStart(Animation animation) {
			runAnimationCount++;
		}

		/**
		 * 当动画结束时回调
		 */
		@Override
		public void onAnimationEnd(Animation animation) {
			runAnimationCount--;
		}

		/**
		 * 当动画重复执行时回调
		 */
		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
	}
}
