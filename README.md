# SHA with RSA
Demonstrating how to implement digital signatures in a original way.

First the content to be signed is first reduced to a message digest with a message-digest algorithm (such as MD5), and then
an octet string containing the message digest is encrypted with the RSA private key of the signer of the content.
The content and the encrypted message digest are represented together according to the syntax in PKCS #7 to yield a digital
signature. This application is compatible with Privacy-Enhanced Mail (PEM) methods.
