package com.example.fumiaki.mylistviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        // データを準備
        ArrayList<User> users = new ArrayList<>();

        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] names = {
                "taguchi",
                "Fkoji",
                "dotinstall"
        };

        String[] locs = {
                "Ebisu",
                "shibuya",
                "tokyo"
        };

        for (int i = 0; i < icons.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setName(names[i]);
            user.setLoc(locs[i]);
            users.add(user);
        }

        UserAdapter adapter = new UserAdapter(this, 0, users);

        // ListViewに表示
        myListView.setAdapter(adapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,    // タップされたView
                    int i,        // 何番目？
                    long id       // view id
            ) {
                TextView name = (TextView) view.findViewById(R.id.name);
                Toast.makeText(
                        MainActivity.this,
                        Integer.toString(i) + ":" + name.getText().toString(),
                        Toast.LENGTH_SHORT
                ).show();
                name.setText("Tapped");
            }
        });
    }

    public class UserAdapter extends ArrayAdapter<User> {

        private LayoutInflater layoutInflater;

        public UserAdapter(Context c, int id, ArrayList<User> users) {
            super(c, id, users);
            this.layoutInflater = (LayoutInflater) c.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = layoutInflater.inflate(
                        R.layout.list_item,
                        parent,
                        false
                );
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.loc  = (TextView) convertView.findViewById(R.id.loc);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            User user = (User) getItem(pos);

            holder.icon.setImageBitmap(user.getIcon());
            holder.name.setText(user.getName());
            holder.loc.setText(user.getLoc());

            return convertView;
        }
    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView loc;

    }

    public class User {
        private Bitmap icon;
        private String name;
        private String loc;


        public Bitmap getIcon() {
            return icon;
        }

        public String getName() {
            return name;
        }

        public String getLoc() {
            return loc;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }
    }
}