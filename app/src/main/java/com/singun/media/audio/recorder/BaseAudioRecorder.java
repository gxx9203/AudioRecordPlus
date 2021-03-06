package com.singun.media.audio.recorder;

import android.os.Build;

import com.singun.media.audio.AudioConfig;
import com.singun.media.audio.processor.AudioProcessor;

/**
 * Created by singun on 2017/3/8 0008.
 */

public class BaseAudioRecorder {
    private AudioProcessor mAudioProcessor;

    protected static boolean isSupportRecorderSession() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public void setAudioProcessor(AudioProcessor audioProcessor) {
        mAudioProcessor = audioProcessor;
    }

    protected void processAudioData(AudioConfig audioConfig, int length) {
        if (mAudioProcessor == null || !mAudioProcessor.processAudioData(audioConfig, length)) {
            audioConfig.audioDataOut = audioConfig.audioDataIn;
        }
    }

    protected short[] processAudioData(short[] dataIn, int length) {
        if (mAudioProcessor == null) {
            return dataIn;
        }
        short[] dataOut = mAudioProcessor.processAudioData(dataIn, length);
        if (dataOut == null) {
            dataOut = dataIn;
        }
        return dataOut;
    }
}
