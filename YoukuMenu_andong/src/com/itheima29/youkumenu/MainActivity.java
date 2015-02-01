package com.itheima29.youkumenu;

import com.itheima29.youkumenu.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

    private RelativeLayout rlLevel1;
	private RelativeLayout rlLevel2;
	private RelativeLayout rlLevel3;
	
	private boolean isDisplayLevel3 = true; 	// 三级菜单默认情况下是显示状态
	private boolean isDisplayLevel2 = true; 	// 二级菜单默认情况下是显示状态
	private boolean isDisplayLevel1 = true; 	// 一级菜单默认情况下是显示状态
	
	private boolean isDisplayMenu = true;		// 所有的菜单默认情况下是显示状态

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
    	rlLevel1 = (RelativeLayout) findViewById(R.id.rl_level1);
    	rlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
    	rlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);
    	
    	findViewById(R.id.ib_home).setOnClickListener(this);
    	findViewById(R.id.ib_menu).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_home:
			// 判断动画是否正在执行中
			if(Utils.runAnimationCount != 0) {
				return;
			}
			
			if(isDisplayLevel2) {
				long startOffset = 0;
				// 判断三级菜单是否显示, 如果显示, 先让三级菜单出去.
				if(isDisplayLevel3) {
					Utils.startOutAnimation(rlLevel3, 0);
					isDisplayLevel3 = !isDisplayLevel3;
					startOffset += 200;
				}
				// 把二级菜单隐藏. 
				Utils.startOutAnimation(rlLevel2, startOffset);
			} else {		// 二级菜单隐藏的状态
				Utils.startInAnimation(rlLevel2, 0);
			}
			isDisplayLevel2 = !isDisplayLevel2;
			break;
		case R.id.ib_menu:
			// 判断动画是否正在执行中
			if(Utils.runAnimationCount != 0) {
				return;
			}
			
			if(isDisplayLevel3) {
				Utils.startOutAnimation(rlLevel3, 0);
			} else {
				Utils.startInAnimation(rlLevel3, 0);
			}
			isDisplayLevel3 = !isDisplayLevel3;
			break;
		default:
			break;
		}
	}

	/**
	 * 当按键被按下时判断按的按钮是否是菜单键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU) {

			// 判断动画是否正在执行中
			if(Utils.runAnimationCount != 0) {
				return true;
			}
			
			
			// 判断一级菜单是否显示或者隐藏
			if(isDisplayMenu) {	// 所有的菜单是显示的状态
				// 把三个菜单都隐藏
				long startOffset = 0;		// 延时时间
				if(isDisplayLevel3) {
					Utils.startOutAnimation(rlLevel3, startOffset);
					startOffset += 200;
					isDisplayLevel3 = !isDisplayLevel3;
				}
				
				if(isDisplayLevel2) {
					Utils.startOutAnimation(rlLevel2, startOffset);
					startOffset += 200;
					isDisplayLevel2 = !isDisplayLevel2;
				}

				if(isDisplayLevel1) {
					Utils.startOutAnimation(rlLevel1, startOffset);
					isDisplayLevel1 = !isDisplayLevel1;
				}
			} else {	// 当前所有的菜单处于隐藏的状态
				// 把三个菜单都显示
				long startOffset = 0;		// 延时时间
				
				if(!isDisplayLevel1) {
					Utils.startInAnimation(rlLevel1, startOffset);
					startOffset += 200;
					isDisplayLevel1 = !isDisplayLevel1;
				}

				if(!isDisplayLevel2) {
					Utils.startInAnimation(rlLevel2, startOffset);
					startOffset += 200;
					isDisplayLevel2 = !isDisplayLevel2;
				}

				if(!isDisplayLevel3) {
					Utils.startInAnimation(rlLevel3, startOffset);
					isDisplayLevel3 = !isDisplayLevel3;
				}
			}
			isDisplayMenu = !isDisplayMenu;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
