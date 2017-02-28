def setName(value) {
    privateName = value
}
def getName() {
    privateName
}
def caution(message) {
    echo "Hello, ${name}! CAUTION: ${message}"
}
