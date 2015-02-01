package com.itheima29.youkumenu.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;

public class Utils {
	
	public static int runAnimationCount = 0;	// ��ǰ���ж���������

	/**
	 * ��ȥ�Ķ���
	 * @param view
	 */
	public static void startOutAnimation(ViewGroup viewGroup, long startOffset) {
		// ��Ҫ��viewgroup�����е��Ӳ�����Ϊ������
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setEnabled(false);		// ��ǰ�����Ա����
		}
		
		
		RotateAnimation ra = new RotateAnimation(
				0f, -180f,	// ��ʼ�ͽ����ĽǶ� 
				Animation.RELATIVE_TO_SELF, 0.5f,	// x��Ĳ������ֵ��: �Լ���ȵ�һ�� 
				Animation.RELATIVE_TO_SELF, 1f);	// y�ߵĲ������ֵ��: �Լ��ĸ߶�
		ra.setDuration(500);
		ra.setFillAfter(true);	// ���ö���ֹͣ�ڶ���������״̬��
		ra.setStartOffset(startOffset);	// ���ö�����ʼִ�е���ʱʱ��
		ra.setAnimationListener(new MyAnimationListener());
		viewGroup.startAnimation(ra);		// ִ�ж���
	}

	/**
	 * �����Ķ���
	 * @param view
	 */
	public static void startInAnimation(ViewGroup viewGroup, long startOffset) {
		// ��Ҫ��viewgroup�����е��Ӳ�����Ϊ����
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setEnabled(true);		// ��ǰ���Ա����
		}
		
		RotateAnimation ra = new RotateAnimation(
				-180f, 0f,	// ��ʼ�ͽ����ĽǶ� 
				Animation.RELATIVE_TO_SELF, 0.5f,	// x��Ĳ������ֵ��: �Լ���ȵ�һ�� 
				Animation.RELATIVE_TO_SELF, 1f);	// y�ߵĲ������ֵ��: �Լ��ĸ߶�
		ra.setDuration(500);
		ra.setFillAfter(true);	// ���ö���ֹͣ�ڶ���������״̬��
		ra.setStartOffset(startOffset);
		ra.setAnimationListener(new MyAnimationListener());
		viewGroup.startAnimation(ra);		// ִ�ж���
	}
	
	static class MyAnimationListener implements AnimationListener {

		/**
		 * ��������ʼִ��ʱ�ص�
		 */
		@Override
		public void onAnimationStart(Animation animation) {
			runAnimationCount++;
		}

		/**
		 * ����������ʱ�ص�
		 */
		@Override
		public void onAnimationEnd(Animation animation) {
			runAnimationCount--;
		}

		/**
		 * �������ظ�ִ��ʱ�ص�
		 */
		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
	}
}
