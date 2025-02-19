package eu.htic.duquesgame.animations;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;

import eu.htic.duquesgame.R;

public class ConfettiEffect {
    private final Context context;
    private final LinearLayout container;
    private static final int CONFETTI_COUNT = 300;
    private static final int ANIMATION_DURATION = 3000;

    public ConfettiEffect(Context context, LinearLayout container) {
        this.context = context;
        this.container = container;
    }

    public void startConfetti() {
        Random random = new Random();
        for (int i = 0; i < CONFETTI_COUNT; i++) {
            ImageView confetti = new ImageView(context);
            confetti.setImageResource(R.drawable.confetti_shape);
            confetti.setLayoutParams(new FrameLayout.LayoutParams(80, 80));
            confetti.setColorFilter(getRandomColor(random));

            int startX = random.nextInt(container.getWidth());
            int startY = random.nextInt(container.getHeight() / 2);
            confetti.setX(startX);
            confetti.setY(startY);

            container.addView(confetti);

            animateConfetti(confetti);
        }
    }

    private void animateConfetti(ImageView confetti) {
        ValueAnimator animator = ValueAnimator.ofFloat(confetti.getY(), container.getHeight());
        animator.setDuration(ANIMATION_DURATION);
        animator.addUpdateListener(animation -> confetti.setY((Float) animation.getAnimatedValue()));

        animator.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationEnd(Animator animation) {
                container.removeView(confetti);
            }
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });

        animator.start();
    }

    private int getRandomColor(Random random) {
        int[] colors = {
                Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,
                Color.MAGENTA, Color.CYAN, Color.WHITE, Color.LTGRAY
        };
        return colors[random.nextInt(colors.length)];
    }
}
