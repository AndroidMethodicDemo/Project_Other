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
	
	private boolean isDisplayLevel3 = true; 	// �����˵�Ĭ�����������ʾ״̬
	private boolean isDisplayLevel2 = true; 	// �����˵�Ĭ�����������ʾ״̬
	private boolean isDisplayLevel1 = true; 	// һ���˵�Ĭ�����������ʾ״̬
	
	private boolean isDisplayMenu = true;		// ���еĲ˵�Ĭ�����������ʾ״̬

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
			// �ж϶����Ƿ�����ִ����
			if(Utils.runAnimationCount != 0) {
				return;
			}
			
			if(isDisplayLevel2) {
				long startOffset = 0;
				// �ж������˵��Ƿ���ʾ, �����ʾ, ���������˵���ȥ.
				if(isDisplayLevel3) {
					Utils.startOutAnimation(rlLevel3, 0);
					isDisplayLevel3 = !isDisplayLevel3;
					startOffset += 200;
				}
				// �Ѷ����˵�����. 
				Utils.startOutAnimation(rlLevel2, startOffset);
			} else {		// �����˵����ص�״̬
				Utils.startInAnimation(rlLevel2, 0);
			}
			isDisplayLevel2 = !isDisplayLevel2;
			break;
		case R.id.ib_menu:
			// �ж϶����Ƿ�����ִ����
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
	 * ������������ʱ�жϰ��İ�ť�Ƿ��ǲ˵���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU) {

			// �ж϶����Ƿ�����ִ����
			if(Utils.runAnimationCount != 0) {
				return true;
			}
			
			
			// �ж�һ���˵��Ƿ���ʾ��������
			if(isDisplayMenu) {	// ���еĲ˵�����ʾ��״̬
				// �������˵�������
				long startOffset = 0;		// ��ʱʱ��
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
			} else {	// ��ǰ���еĲ˵��������ص�״̬
				// �������˵�����ʾ
				long startOffset = 0;		// ��ʱʱ��
				
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
