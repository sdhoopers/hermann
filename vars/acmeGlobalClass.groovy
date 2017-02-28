// vars/acme.groovy
namePrivate = 'human'

def setName(value) {
    namePrivate = value
}
def getName() {
    namePrivate
}
def caution(message) {
    echo "Hello, ${namePrivate}! CAUTION: ${message}"
}
