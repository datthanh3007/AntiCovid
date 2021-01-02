package com.example.anticovid.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anticovid.Activity.ActivityDeclareHealth;
import com.example.anticovid.Activity.ActivityDeclaretion;
import com.example.anticovid.Model.Infected;
import com.example.anticovid.R;
//import com.example.anticovid.Service.APIDataService;
import com.example.anticovid.Service.ApiInfected.APIservice;
import com.example.anticovid.Service.ApiInfected.Dataservice;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragmentHome extends Fragment implements OnMapReadyCallback {
    private static final int REQUEST_LOCATION_PERMISSION = 45;
    private static final String CHANNEL_ID ="ChancelHN" ;
    private String strMesage = "Bạn đã di chuyển gần khu vực có dịch";
    private String strTitle="ENDCOVI";
    private ArrayList<Infected> infectedArrayList;
    private GoogleMap map;
    private LocationManager locationManager;
    private FusedLocationProviderClient client;
    private SupportMapFragment mMapFragment;
    private View view;
    private ImageView imgcall;
    private CardView crvDclaration;
    private  CardView crvDeclareHealth;
    public static  ArrayList<Infected> locationArr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crvDclaration = view.findViewById(R.id.crvDeclration);
        crvDeclareHealth = view.findViewById(R.id.crvDeclaHealth);
        imgcall=view.findViewById(R.id.btncall);
        infectedArrayList = new ArrayList<>();

//        btnGuide = view.findViewById(R.id.btnGuide);
        //frameLayout = view.findViewById(R.id.framelayout);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frmyMap);

        client = LocationServices.getFusedLocationProviderClient(requireActivity());

        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
            createNotificationChannel();
            startLocationUpdates();
        }
        getData();
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" +19009095;
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }
        });
        crvDclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityDeclaretion.class);
                startActivity(intent);
            }
        });
        crvDeclareHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityDeclareHealth.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        stopLocationUpdates();
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
        if (client != null) {
            Log.d("aaa", "client");
            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(final Location location) {
                    Log.d("aaa", "vao");
                    if (location != null) {
                        Log.d("aaa", "notnull");
                        mMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                int height = 100;
                                int width = 100;
                                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.mylocation);
                                Bitmap b = bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                                        .title("")
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)); //defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                googleMap.addMarker(markerOptions);
                            }
                        });

                    }
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getActivity(), "aaa" + infectedArrayList.size(), Toast.LENGTH_LONG).show();
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        map.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney"))
//                .setSnippet("The most popular");
//        Marker markerSydney = map.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney"));
        map = googleMap;
        final LatLng melbourneLocation = new LatLng(-37.813, 144.962);
        Marker melbourne = map.addMarker(
                new MarkerOptions()
                        .position(melbourneLocation)
                        .title("Melbourne")
                        .snippet("Population: 4,137,400")
        );
        map.moveCamera(CameraUpdateFactory.newLatLng(melbourneLocation));
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longtitude = location.getLongitude();
            double lat = location.getLatitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    //getAPI
    private void getData() {
        Dataservice dataservice = APIservice.getService();
        Call<List<Infected>> callback = dataservice.getdataInfected();
        callback.enqueue(new Callback<List<Infected>>() {
            @Override
            public void onResponse(Call<List<Infected>> call, Response<List<Infected>> response) {
                infectedArrayList = (ArrayList<Infected>) response.body();
                locationArr = (ArrayList<Infected>) response.body();
                mMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        int height = 100;
                        int width = 100;
                        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.virusfinal);
                        Bitmap b = bitmapdraw.getBitmap();
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        for (int i = 0; i < infectedArrayList.size(); i++) {
                            double longi = Double.parseDouble(infectedArrayList.get(i).getLongitude());
                            double lati = Double.parseDouble(infectedArrayList.get(i).getLatitude());
                            String name = infectedArrayList.get(i).getNameInfected();
                            String address = infectedArrayList.get(i).getAddress();
                            map = googleMap;
                            final LatLng melbourneLocation = new LatLng(longi, lati);
                            Marker melbourne = map.addMarker(
                                    new MarkerOptions()
                                            .position(melbourneLocation)
                                            .title(name)
                                            .snippet(address)
                                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                            );
                            map.moveCamera(CameraUpdateFactory.newLatLng(melbourneLocation));
                        }
                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                int x = 0;
                                String name = marker.getTitle();
                                String add = "";
                                String note = "";
                                String time = "";
                                for (int i = 0; i < infectedArrayList.size(); i++) {
                                    if (name.equals(infectedArrayList.get(i).getNameInfected())) {
                                        x = i;
                                        add = infectedArrayList.get(x).getAddress();
                                        note = infectedArrayList.get(x).getNote();
                                        time = infectedArrayList.get(x).getTime();
                                        break;
                                    }
                                }

                                fragmentDialogInfected fragmentDialogInfected = new fragmentDialogInfected();
                                Bundle bundle = new Bundle();
                                bundle.putString("name", name);
                                bundle.putString("address", add);
                                bundle.putString("note", note);
                                bundle.putString("time", time);
                                fragmentDialogInfected.setArguments(bundle);
                                fragmentDialogInfected.show(getFragmentManager(), "Myfragment");
                                return false;
                            }
                        });
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Infected>> call, Throwable t) {
                //    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }

    private FusedLocationProviderClient fusedLocationClient;

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(TimeUnit.SECONDS.toMillis(60));
        locationRequest.setFastestInterval(TimeUnit.SECONDS.toMillis(30));
        locationRequest.setMaxWaitTime(TimeUnit.MINUTES.toMillis(2));
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private PendingIntent locationUpdatePendingIntent() {
        Intent intent = new Intent(requireContext(), LocationUpdatesBroadcastReceiver.class);
//        intent.data = Uri.parse(listCaseInformation.toString());
        intent.setAction("abc");
        return PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void startLocationUpdates() {
        try {
            fusedLocationClient  = LocationServices.getFusedLocationProviderClient(requireContext());
            fusedLocationClient.requestLocationUpdates(getLocationRequest(), locationUpdatePendingIntent());
        } catch (SecurityException permissionRevoked) {
            Log.d("TAG", "Location permission revoked; details: $permissionRevoked");
            throw permissionRevoked;
        }
    }

    private void stopLocationUpdates() {
        Log.d("TAG", "stopLocationUpdates()");
        fusedLocationClient.removeLocationUpdates(locationUpdatePendingIntent());
    }
    public void createNotification(){
        Intent notification  = new Intent(getActivity(),fragmentDialogInfected.class);
        notification.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notification.putExtra("message",strMesage);
        notification.putExtra("title",strTitle);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, notification, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                 .setSmallIcon(R.drawable.avatarapp)
                .setContentTitle(strTitle)
                .setContentText(strMesage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager manager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
