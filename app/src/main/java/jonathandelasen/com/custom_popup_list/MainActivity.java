package jonathandelasen.com.custom_popup_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText acetStatus;
    private ListPopupWindow statusPopupList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        acetStatus = findViewById(R.id.acet_status);
        setPopupList();
        //we need to show the list when clicking on the field
        setListeners();
    }

    private void setListeners() {
        acetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusPopupList.show();
            }
        });
    }

    private void setPopupList() {
        final List<String> status = new ArrayList<>();
        status.add("Status 1");
        status.add("Status 2");
        status.add("Status 3");
        status.add("Status 4");

        statusPopupList = new ListPopupWindow(MainActivity.this);
        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_simple_status, R.id.tv_element, status);
        statusPopupList.setAnchorView(acetStatus); //this let as set the popup below the EditText
        statusPopupList.setAdapter(adapter);
        statusPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acetStatus.setText(status.get(position));//we set the selected element in the EditText
                statusPopupList.dismiss();
            }
        });
    }
}
