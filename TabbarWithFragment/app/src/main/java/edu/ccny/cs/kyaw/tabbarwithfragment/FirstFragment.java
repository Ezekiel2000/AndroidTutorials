gpackage edu.ccny.cs.kyaw.tabbarwithfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {



    private String title;
    private  int page;

    public static FirstFragment newInstance (int page, String title){
        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("SomeInt", page);
        bundle.putString("someTitle", title);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }



    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("SomeInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        TextView textViewLb = (TextView) view.findViewById(R.id.tvLabel);
        textViewLb.setText(page + "----"+title);
        return view;
    }


}
