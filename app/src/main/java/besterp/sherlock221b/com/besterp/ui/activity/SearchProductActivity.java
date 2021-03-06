package besterp.sherlock221b.com.besterp.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.ui.common.DrawerActivity;

public class SearchProductActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        DrawerMenuModel dm = getMenuItem(this.getIntent());
        setTitle(dm.getMenuName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
