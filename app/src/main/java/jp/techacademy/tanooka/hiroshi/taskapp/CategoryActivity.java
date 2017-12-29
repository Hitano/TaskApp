package jp.techacademy.tanooka.hiroshi.taskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoryActivity extends AppCompatActivity {
    private EditText mCategoryEdit;
    private Button mSaveCategory;
    private Category mCategory;

    private View.OnClickListener mOnDoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCategory();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // ActionBarを設定する
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSaveCategory = (Button)findViewById(R.id.save_category_button);
        mSaveCategory.setOnClickListener(mOnDoneClickListener);
        mCategoryEdit = (EditText)findViewById(R.id.category_edit_text);
    }

    private void addCategory() {
        // カテゴリを新規作成する処理を書く
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        if (mCategory == null) {
            // 新規作成の場合
            mCategory = new Category();

            RealmResults<Category> categoryRealmResults = realm.where(Category.class).findAll();

            int identifier;
            if (categoryRealmResults.max("id") != null) {
                identifier = categoryRealmResults.max("id").intValue() + 1;
            } else {
                identifier = 0;
            }
            mCategory.setId(identifier);
        }

        String category = mCategoryEdit.getText().toString();
        mCategory.setCategory(category);

        realm.copyToRealmOrUpdate(mCategory);
        realm.commitTransaction();

        realm.close();
    }
}
