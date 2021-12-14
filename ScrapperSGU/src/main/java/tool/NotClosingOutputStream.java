package tool;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Represents an {@code OutputStream} that does not close the underlying output stream on a call to {@link #close()}.
 * This may be useful for encapsulating an {@code OutputStream} into other output streams that does not have to be
 * closed, while closing the outer streams or reader.
 */
public class NotClosingOutputStream extends OutputStream {

    /** The underlying output stream. */
    private final OutputStream out;

    /**
     * Creates a new output stream that does not close the given output stream on a call to {@link #close()}.
     * 
     * @param out
     *            the output stream
     */
    public NotClosingOutputStream(final OutputStream out) {
        this.out = out;
    }

    /*
     * DELEGATION TO OUTPUT STREAM
     */

    @Override
    public void close() throws IOException {
        // do nothing here, since we don't want to close the underlying input stream
    }

    @Override
    public void write(final int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(final byte[] b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }
}