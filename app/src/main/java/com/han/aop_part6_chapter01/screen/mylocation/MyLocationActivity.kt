package com.han.aop_part6_chapter01.screen.mylocation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.han.aop_part6_chapter01.R
import com.han.aop_part6_chapter01.data.entity.LocationLatLngEntity
import com.han.aop_part6_chapter01.data.entity.MapSearchInfoEntity
import com.han.aop_part6_chapter01.databinding.ActivityMyLocationBinding
import com.han.aop_part6_chapter01.screen.base.BaseActivity
import com.han.aop_part6_chapter01.screen.main.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MyLocationActivity : BaseActivity<MyLocationViewModel, ActivityMyLocationBinding>(),
    OnMapReadyCallback {

    override val viewModel by viewModel<MyLocationViewModel> {
        parametersOf(
            intent.getParcelableExtra<MapSearchInfoEntity>(HomeViewModel.MY_LOCATION_KEY)
        )
    }

    override fun getViewBinding(): ActivityMyLocationBinding =
        ActivityMyLocationBinding.inflate(layoutInflater)

    private lateinit var map: GoogleMap
    private var isMapInitialized: Boolean = false
    private var isChangeLocation: Boolean = false
    override fun onMapReady(map: GoogleMap) {
        this.map = map ?: return
        viewModel.fetchData()
    }

    override fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            finish()
        }
        confirmButton.setOnClickListener {
            viewModel.confirmSelectLocation()
        }
        setupGoogleMap()
    }

    override fun observeData() = viewModel.myLocationStateLiveData.observe(this) {
        when (it) {
            is MyLocationState.Loading -> {
                handleLoadingState()
            }
            is MyLocationState.Success -> {
                if (::map.isInitialized) {
                    handleSuccessState(it)
                }

            }
            is MyLocationState.Confirm -> {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(HomeViewModel.MY_LOCATION_KEY, it.mapSearchInfoEntity)
                })
                finish()
            }
            is MyLocationState.Error -> {
                Toast.makeText(this, it.messageId, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }

    }

    private fun handleLoadingState() = with(binding) {
        locationLoading.isVisible = true
        locationTitleText.text = getString(R.string.loading)
    }

    private fun handleSuccessState(state: MyLocationState.Success) = with(binding) {
        val mapSearchInfo = state.mapSearchInfoEntity
        locationLoading.isGone = true
        locationTitleText.text = mapSearchInfo.fullAddress
        if (isMapInitialized.not()) {
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        mapSearchInfo.locationLatLng.latitude,
                        mapSearchInfo.locationLatLng.longitude
                    ), CAMERA_ZOOM_LEVEL
                )
            )
            map.setOnCameraIdleListener {
                if (isChangeLocation.not()) {
                    isChangeLocation = true
                    Handler(Looper.getMainLooper()).postDelayed({
                        val cameraLng = map.cameraPosition.target
                        viewModel.changeLocationInfo(
                            LocationLatLngEntity(
                                cameraLng.latitude,
                                cameraLng.longitude
                            )
                        )
                        isChangeLocation = false
                    }, 1000)
                }
            }
            isMapInitialized = true
        }
    }

    companion object {
        const val CAMERA_ZOOM_LEVEL = 17f
        fun newIntent(context: Context, mapSearchInfoEntity: MapSearchInfoEntity) =
            Intent(context, MyLocationActivity::class.java).apply {
                putExtra(HomeViewModel.MY_LOCATION_KEY, mapSearchInfoEntity)
            }
    }


    private fun setupGoogleMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


}