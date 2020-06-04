package sg.edu.rp.soi.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lv;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.add);
        btnDelete = findViewById(R.id.del);
        btnClear = findViewById(R.id.clear);
        lv = findViewById(R.id.lv);
        spin = findViewById(R.id.spinner);
        final ArrayList<String> alTask = new ArrayList<String>();
        final ArrayAdapter<String> aaTask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTask);
        lv.setAdapter(aaTask);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etTask.setHint("Type in a new task");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                alTask.add(task);
                etTask.setText("");
                aaTask.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTask.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Your input is empty. Please key in a number", Toast.LENGTH_LONG).show();
                    return;
                }
                int intTask = Integer.parseInt(etTask.getText().toString());
                if (alTask.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                }else if (alTask.size() <= intTask){
                    etTask.setText("");
                    Toast.makeText(MainActivity.this, "Wrong index number. index number must be lower than "+alTask.size(), Toast.LENGTH_SHORT).show();
                }else{
                    alTask.remove(intTask);
                    etTask.setText("");
                    aaTask.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "List cleared", Toast.LENGTH_SHORT).show();
                etTask.setText("");
            }
        });
    }
}