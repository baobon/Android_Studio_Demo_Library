package bao.bon.googlearcoreanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private AnchorNode anchorNode;
    private ModelAnimator animator;
    private int nextAnimation;
    private FloatingActionButton btn_anim;
    private ModelRenderable animationCrab;
    private TransformableNode transformableNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                if (animationCrab == null) {
                    return;
                }
                //Creat the Anchor
                Anchor anchor = hitResult.createAnchor();
                if (anchorNode == null) // If Crab is not place on plane
                {
                    anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    transformableNode = new TransformableNode(arFragment.getTransformationSystem());
                    // Scale model
                    transformableNode.getScaleController().setMinScale(0.09f);
                    transformableNode.getScaleController().setMaxScale(0.1f);
                    transformableNode.setParent(anchorNode);
                    transformableNode.setRenderable(animationCrab);

                }
            }
        });

        arFragment.getArSceneView().getScene().addOnUpdateListener(new Scene.OnUpdateListener() {
            @Override
            public void onUpdate(FrameTime frameTime) {
                if (anchorNode == null) {
                    if (btn_anim.isEnabled()) {
                        btn_anim.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                        btn_anim.setEnabled(false);
                    }
                } else {
                    if (!btn_anim.isEnabled()) {
                        btn_anim.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.colorPrimary));
                        btn_anim.setEnabled(true);
                    }
                }
            }
        });

        btn_anim = findViewById(R.id.btn_anim);
        btn_anim.setEnabled(false);
        btn_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animator == null || !animator.isRunning()) {
                    AnimationData data = animationCrab.getAnimationData(nextAnimation);
                    nextAnimation = (nextAnimation + 1) % animationCrab.getAnimationDataCount();
                    animator = new ModelAnimator(data, animationCrab);
                    animator.start();
                }
            }
        });

        setUpModel();
    }

    private void setUpModel() {
        ModelRenderable.builder()
                .setSource(this, R.raw.cangrejo)
                .build()
                .thenAccept(rederable -> animationCrab = rederable)
                .exceptionally(throwalbe -> {
                    Toast.makeText(this, "" + throwalbe.getMessage(), Toast.LENGTH_SHORT).show();
                    return null;
                });

    }
}
