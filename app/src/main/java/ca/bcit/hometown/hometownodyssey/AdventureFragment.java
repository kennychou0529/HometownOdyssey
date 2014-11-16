package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdventureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdventureFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AdventureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdventureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdventureFragment newInstance(String param1, String param2) {
        AdventureFragment fragment = new AdventureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public AdventureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view
        View root = inflater.inflate(R.layout.fragment_adventure, container, false);

        // Get image references
        ImageView bg_layer1 = (ImageView) root.findViewById( R.id.bg_layer1 );
        ImageView bg_layer2 = (ImageView) root.findViewById( R.id.bg_layer2 );
        ImageView bg_layer3 = (ImageView) root.findViewById( R.id.bg_layer3 );
        ImageView foreground = (ImageView) root.findViewById(R.id.fg_layer);

        ImageView bg_layer1_2 = (ImageView) root.findViewById(R.id.bg_layer1_2);
        ImageView bg_layer2_2 = (ImageView) root.findViewById(R.id.bg_layer2_2);
        ImageView bg_layer3_2 = (ImageView) root.findViewById(R.id.bg_layer3_2);
        ImageView foreground_2 = (ImageView) root.findViewById(R.id.fg_layer_2);


        ImageView char_head = (ImageView) root.findViewById(R.id.char_head);
        ImageView char_torso = (ImageView) root.findViewById(R.id.char_torso);
        ImageView char_arm = (ImageView) root.findViewById(R.id.char_arm);
        ImageView char_arm_2 = (ImageView) root.findViewById(R.id.char_arm2);
        ImageView char_leg = (ImageView) root.findViewById(R.id.char_leg);
        ImageView char_leg_2 = (ImageView) root.findViewById(R.id.char_leg2);
        ImageView char_foot = (ImageView) root.findViewById(R.id.char_foot);
        ImageView char_foot_2 = (ImageView) root.findViewById(R.id.char_foot2);


        // Create animations
        Animation a_bg_layer1 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer1 );
        Animation a_bg_layer2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer2 );
        Animation a_bg_layer3 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer3 );
        Animation a_fg_grass = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_fg );

        Animation a_bg_layer1_2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer1 );
        Animation a_bg_layer2_2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer2 );
        Animation a_bg_layer3_2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_bg_layer3 );
        Animation a_fg_grass_2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_fg );

        Animation a_char_arm = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_char_arm);
        Animation a_char_arm2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_char_arm2);
        Animation a_char_leg = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_char_leg);
        Animation a_char_leg2 = AnimationUtils.loadAnimation( root.getContext(), R.anim.anim_char_leg2);

        // Set image segment start offsets
        WindowManager wm = (WindowManager) root.getContext().getSystemService( Context.WINDOW_SERVICE );
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize( size );
        bg_layer1_2.setTranslationX( size.x );
        bg_layer2_2.setTranslationX( size.x );
        bg_layer3_2.setTranslationX( size.x );
        foreground_2.setTranslationX( size.x );

        //Set image segment images
        char_head.setImageResource( Item.getHeadImage( root.getContext() ) );
        char_torso.setImageResource( Item.getBodyImages( root.getContext() ).get( 0 ) );
        char_arm.setImageResource( Item.getBodyImages( root.getContext() ).get( 1 ) );
        char_arm_2.setImageResource( Item.getBodyImages( root.getContext() ).get( 2 ) );
        char_leg.setImageResource( Item.getLegImages( root.getContext() ).get( 0 ) );
        char_leg_2.setImageResource( Item.getLegImages( root.getContext() ).get( 1 ) );
        char_foot.setImageResource( Item.getFootImages( root.getContext() ).get( 0 ) );
        char_foot_2.setImageResource( Item.getFootImages( root.getContext() ).get( 1 ) );

        // Set image segment animations
        bg_layer1.setAnimation( a_bg_layer1 );
        bg_layer2.setAnimation( a_bg_layer2 );
        bg_layer3.setAnimation( a_bg_layer3 );
        foreground.setAnimation( a_fg_grass );

        bg_layer1_2.setAnimation( a_bg_layer1_2 );
        bg_layer2_2.setAnimation( a_bg_layer2_2 );
        bg_layer3_2.setAnimation( a_bg_layer3_2 );
        foreground_2.setAnimation( a_fg_grass_2 );


        char_arm.setAnimation( a_char_arm );
        char_arm_2.setAnimation( a_char_arm2 );
        char_leg.setAnimation( a_char_leg );
        char_leg_2.setAnimation( a_char_leg2 );
        char_foot.setAnimation( a_char_leg );
        char_foot_2.setAnimation( a_char_leg2 );

        // Inflate the layout for this fragment
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
