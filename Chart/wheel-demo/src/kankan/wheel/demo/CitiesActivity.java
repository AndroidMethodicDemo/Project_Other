package kankan.wheel.demo;

import java.util.ArrayList;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CitiesActivity extends Activity {
    // Scrolling flag
    private boolean scrolling = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cities_layout);
                
        final WheelView country = (WheelView) findViewById(R.id.country);
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<100;i++){
        	list.add(i+"");
        }
        country.setVisibleItems(3);
        country.setViewAdapter(new CountryAdapter(this,list));

        /*final String cities[][] = new String[][] {
        		new String[] {"New York", "Washington", "Chicago", "Atlanta", "Orlando"},
        		new String[] {"Ottawa", "Vancouver", "Toronto", "Windsor", "Montreal"},
        		new String[] {"Kiev", "Dnipro", "Lviv", "Kharkiv"},
        		new String[] {"Paris", "Bordeaux"},
        		};
        */
       // final WheelView city = (WheelView) findViewById(R.id.city);
      //  city.setVisibleItems(5);

        // 设置变化监听器
        country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
			    if (!scrolling) {
			    }
			}
		});
        // 设置滚动监听器
        country.addScrollingListener( new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
               //updateCities(city, cities, country.getCurrentItem());
            }
        });

        country.setCurrentItem(1);
    }
    
    /**
     * Updates the city wheel
     */
   /* private void updateCities(WheelView city, String cities[][], int index) {
        ArrayWheelAdapter<String> adapter =
            new ArrayWheelAdapter<String>(this, cities[index]);
        adapter.setTextSize(18);
        city.setViewAdapter(adapter);
        city.setCurrentItem(cities[index].length / 2);        
    }*/
    
    /**
     * Adapter for countries
     */
    private class CountryAdapter extends AbstractWheelTextAdapter {
        // Countries names
    	ArrayList<String> list;
        protected CountryAdapter(Context context,ArrayList<String> list) {
        	//layout是一个文本+摄氏度图标的布局
            super(context, R.layout.tempitem, NO_RESOURCE);
            this.list = list;
            // 这个其实可以写在上面的super的第三个参数中
            setItemTextResource(R.id.tempValue);
        }

        // Countries flags
        private int flags =R.drawable.tem_unit_dialog;

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
        	// 调用父类的getItem
            View view = super.getItem(index, cachedView, parent);
            ImageView img = (ImageView) view.findViewById(R.id.tempImag);
            img.setImageResource(flags);
            return view;
        }
        
        @Override
        public int getItemsCount() {
            return list.size();
        }
        /**
         * 返回要显示的文本
         */
        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index)+"";
        }
    }
}
