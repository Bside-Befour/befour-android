package org.positive.daymotion.presentation.start

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import org.positive.daymotion.BuildConfig
import org.positive.daymotion.R
import org.positive.daymotion.databinding.ActivityStartBinding
import org.positive.daymotion.presentation.common.base.BaseActivity
import org.positive.daymotion.presentation.common.base.viewModelOf
import org.positive.daymotion.presentation.common.extension.dpToPx
import org.positive.daymotion.presentation.common.showPopupDialog
import org.positive.daymotion.presentation.login.LoginActivity
import org.positive.daymotion.presentation.root.RootActivity

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    private val viewModel by viewModelOf<StartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkVersionAndToken(BuildConfig.VERSION_NAME)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.konfettiView.doOnLayout { view ->
            val colors = arrayListOf(
                ContextCompat.getColor(this, R.color._FF6666),
                ContextCompat.getColor(this, R.color._FF8566),
                ContextCompat.getColor(this, R.color._FF4685),
                ContextCompat.getColor(this, R.color._FE7BEB),
                ContextCompat.getColor(this, R.color._FFBE00),
                ContextCompat.getColor(this, R.color._46E4FF),
                ContextCompat.getColor(this, R.color._51FFA6)
            )

            (view as KonfettiView).build()
                .addColors(colors)
                .setDirection(210.0, 330.0)
                .setSpeed(1f, 1f)
                .addShapes(createParticleDrawable())
                .setTimeToLive(60000L)
                .addSizes(Size(12))
                .setPosition(-50f, view.width + 50f, -50f, -50f)
                .streamFor(60, 60000L)
//                .setDirection(210.0, 330.0)
//                .addColors(colors)
//                .setSpeed(6f, 8f)
//                .setTimeToLive(5000L)
//                .setPosition(0f, binding.konfettiView.width.toFloat(), 0f, 0f)
//                .streamFor(24, 10000L)
        }
    }

    private fun createParticleDrawable(): Shape {
        val width = dpToPx(11).toInt()
        val height = dpToPx(26).toInt()
        val drawable = GradientDrawable().apply { setSize(width, height) }
        return Shape.DrawableShape(drawable, true)
    }

    private fun setupObservers() {
        viewModel.needUpdate.observe {
            showPopupDialog {
                title = "새로운 버전 업데이트가 필요 합니다."
                content = "..."
                isCancelable = false
                isVisibleGrayButton = false
                onClickBlueButton {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_URL)).apply {
                        setPackage("com.android.vending")
                    }
                    startActivity(intent)
                    finishAffinity()
                }
            }
        }

        viewModel.goToHome.observe {
            LoginActivity.startOnTop(this)
        }

        viewModel.goToLogin.observe {
            RootActivity.startOnTop(this)
        }
    }

    companion object {
        private const val PLAY_STORE_URL =
            "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
    }
}