// vars/acme.groovy
def setName(value) {
    namePrivate = value
}
def getName() {
    namePrivate
}
def caution(message) {
    echo "Hello, ${namePrivate}! CAUTION: ${message}"
}
