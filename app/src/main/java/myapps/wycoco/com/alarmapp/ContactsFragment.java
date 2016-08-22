//package myapps.wycoco.com.alarmapp;
//
//import android.annotation.SuppressLint;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.database.Cursor;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.support.v4.widget.SimpleCursorAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//public class ContactsFragment extends Fragment implements
//        LoaderManager.LoaderCallbacks<Cursor>,
//        AdapterView.OnItemClickListener{
//    @SuppressLint("InlinedApi")
//    private final static String[] FROM_COLUMNS = {
//            Build.VERSION.SDK_INT
//                    >= Build.VERSION_CODES.HONEYCOMB ?
//                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
//                    ContactsContract.Contacts.DISPLAY_NAME
//    };
//
//    private final static int[] TO_IDS = {
//            android.R.id.text1
//    };
//    ListView mContactsList;
//    long mContactId;
//    String mContactKey;
//    Uri mContactUri;
//    private SimpleCursorAdapter mCursorAdapter;
//
//    public ContactsFragment() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the fragment layout
//        return inflater.inflate(R.layout.contacts_fragment, container, false);
//    }
//
//
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        // Gets the ListView from the View list of the parent activity
//        mContactsList = (ListView)getActivity().findViewById(R.layout.);
//        // Gets a CursorAdapter
//        mCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.contacts_fragment, null, FROM_COLUMNS, TO_IDS, 0);
//        // Sets the adapter for the ListView
//        mContactsList.setAdapter(mCursorAdapter);
//    }
//
//
//
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        mSelectionArgs[0] = "%" + mSearchString + "%";
//        // Starts the query
//        return new CursorLoader(
//                getActivity(),
//                ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, mSelectionArgs, null
//
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        mCursorAdapter.swapCursor(cursor);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        mCursorAdapter.swapCursor(null);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//}
