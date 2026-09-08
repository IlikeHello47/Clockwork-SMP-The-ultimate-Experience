package dev.IlikeHello47.clockwork_smp.radio;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;
import java.io.InputStream;
import java.net.URL;

public class MonoRadioThread extends Thread {
    private final String streamUrl;
    private boolean running = true;
    private InputStream stream;

    public MonoRadioThread(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(streamUrl);
            stream = url.openStream();
            Bitstream bitstream = new Bitstream(stream);
            Decoder decoder = new Decoder();

            while (running) {
                Header frameHeader = bitstream.readFrame();
                if (frameHeader == null) break;

                SampleBuffer output = (SampleBuffer) decoder.decodeFrame(frameHeader, bitstream);

                if (output.getChannelCount() == 2) {
                    short[] buffer = output.getBuffer();
                    int length = output.getBufferLength();
                    for (int i = 0; i < length; i += 2) {
                        int left = buffer[i];
                        int right = buffer[i + 1];
                        short monoSample = (short) ((left + right) / 2);
                        buffer[i] = monoSample;
                        buffer[i + 1] = monoSample;
                    }
                }

                bitstream.closeFrame();
            }
        } catch (Exception e) {
            System.err.println("Radio-Stream canceled or URL invalid.");
        }
    }

    public void stopRadio() {
        this.running = false;
        try {
            if (stream != null) stream.close();
        } catch (Exception ignored) {}
    }
}
