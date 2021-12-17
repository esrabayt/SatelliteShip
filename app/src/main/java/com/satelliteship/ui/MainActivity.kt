package com.satelliteship.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.activity.viewModels
import androidx.core.text.bold
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.satelliteship.core.BaseActivity
import com.satelliteship.databinding.ActivityMainBinding
import com.satelliteship.databinding.SatelliteDetailBinding
import com.satelliteship.domain.model.Satellite
import com.satelliteship.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter by lazy {
        MainAdapter(Satellite.DIFF_CALLBACK) {
            onItemClicked(::showSatelliteDetail)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observeViewModel()
    }


    private fun initView() = with(binding) {
        val layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL, false
        )
        mainRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                layoutManager.orientation
            )
        )
        mainRecyclerView.adapter = mainAdapter
    }

    private fun showSatelliteDetail(satellite: Satellite) {
        val detail = viewModel.getDetail(satellite) ?: return
        val binding = SatelliteDetailBinding.inflate(layoutInflater)
        MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            with(binding) {

                title.text = satellite.name
                date.text = detail.first_flight

                height.text = SpannableStringBuilder()
                    .bold { append("Height/Mass:") }
                    .append("${detail.height}/${detail.mass}")

                cost.text = SpannableStringBuilder()
                    .bold { append("Cost:") }
                    .append(detail.cost_per_launch.toString())

                position.text = SpannableStringBuilder()
                    .bold { append("Last Positions:") }
                    .append("(" + viewModel.getPositions(satellite.id)?.posX.toString())
                    .append("," + viewModel.getPositions(satellite.id)?.posY.toString() + ")")
            }

            customView(view = binding.root, noVerticalPadding = true)
            lifecycleOwner(this@MainActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.dataLoading.observe(this) { binding.loadingLayout.isVisible = it }
        viewModel.items.observe(this) { mainAdapter.submitList(it) }
    }
}
