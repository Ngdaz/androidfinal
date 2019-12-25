package com.example.gitbrowser.ui.issues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModel;

import com.example.gitbrowser.R;
import com.example.gitbrowser.ui.news.NewsViewModel;
import com.example.gitbrowser.ui.issues.IssuesViewModel;

public class IssuesFragment extends Fragment {

    private IssuesViewModel issuesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        issuesViewModel =
                ViewModelProviders.of(this).get(IssuesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        issuesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
