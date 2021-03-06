package gradlefs

import org.apache.commons.codec.binary.Hex
import spock.lang.Specification
import spock.lang.TempDir

import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.UserDefinedFileAttributeView

class InMemoryFSTest extends Specification {
    @TempDir
    Path temporaryFolder

    def "creates in memory FS"() {
        when:
        InMemoryFS memfs = new InMemoryFS()
        def parent = temporaryFolder.toFile()
        def file = new File(parent, "foo.txt")

        then:
        memfs.mount(temporaryFolder, false, true)

        when:
        file << """
            this is a file written in memory
        """

        then:
        file.text == """
            this is a file written in memory
        """

        and:
        checksumFromFilesystemOf(file) == 'ca0eb52dd366688004a36ff7b442a0b7'

        cleanup:
        memfs.umount()
    }

    private static String checksumFromFilesystemOf(File file) {
        def attr = "checksum"
        def view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView)
        ByteBuffer buf = ByteBuffer.allocate(view.size(attr))
        view.read(attr, buf)
        buf.flip()
        def array = buf.array()
        Hex.encodeHexString(array)
    }
}
