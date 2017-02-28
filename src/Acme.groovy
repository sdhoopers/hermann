class Acme implements Serializable {
    private final Script script
    private String name = 'human'
    Acme(Script script) {
        this.script = script
    }
    def setName(value) {
        name = value
    }
    def getName() {
        name
    }
    def caution(message) {
        script.echo "Hello, ${name}! CAUTION: ${message}"
    }
}
