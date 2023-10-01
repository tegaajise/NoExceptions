import java.io.BufferedReader;
import java.io.IOException;

public abstract class FileProcessor<R> {

    protected abstract void startFile();
    protected abstract void processLine(String line);
    protected abstract R endFile();

    public final R processFile(BufferedReader in) throws IOException {
        this.startFile();
        try {
            String line;
            // Read and print each line of the file
            while ((line = in.readLine()) != null) {
                processLine(line);
            }

        } catch (IOException e) {
            // Handle any IOException that might occur during file reading
            e.printStackTrace();
        }
        return endFile();
    }

    /*This	 method	 should	first	 call	 the	 method	startFile.
    Next,	 it	 reads	 all	 the	 lines	 of	 text	 coming
    from	in	one	line	at	the	time	in	some	suitable	loop,
    whose	body	calls	the	method	processLine	for
    each	line	that	it	reads	in.
    Once	all	lines	have	been	read	in	and	processed,
    this	method	should	finish
    up	by	calling	the	method	endFile, and return	whatever the method	endFile	returned.*/

}
