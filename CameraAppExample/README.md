# Aplicação com Câmera

Esta página contém uma aplicação que faz o uso da câmera e um pequeno guia de como solicitar a permissão do uso da câmera e como acessá-la na linguagem nativa.

### Especificando recursos e permissões em AndroidManifest.xml

O arquivo **AndroidManifest.xml** desempenha  papel na configuração da aplicação, como os recursos a serem utilizados e as permissões necessárias. Por exemplo o trecho do código abaixo especifica os recursos a serem utilizados (no caso a câmera - qualquer uma que estiver disponível, sem especificar se será a frontal a traseira) e a permissão para utilizá-la.

```
    <uses-feature android:name="android.hardware.camera.any"/>
    <uses-permission android:name="android.permission.CAMERA" />
```

### Especificando dependências em build.gradle

A depender da biblioteca utilizada na implementação, é necessário acrescentá-la no arquivo **build.gradle**. Para o uso da câmera temos algumas das bibliotecas disponíveis neste [link](https://developer.android.com/training/camera/choose-camera-library). Neste projeto, utilizaremos o aplicativo nativo do próprio celular por meio do _Intent_. Assim não será necessário incluir nenhuma biblioteca no arquivo.

### Solicitação de permissões na MainActivity

Ao iniciar a aplicação, seguimos basicamente o fluxo de:
1. Verificar se já temos a permissão do uso da câmera no aplicativo, caso o usuário já tenha concedido anteriormente (linha 23)
```
val hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
```

1. Checar se temos a permissão:
    - Caso negativo, solicitar ao usuário

    - Se a permissão já estiver concedida, seguir com o fluxo (chamada de função, processamento, chamada da função em Python, etc)

```
if (!hasCameraPermission) {
    val permissions = arrayOf(Manifest.permission.CAMERA)
    requestPermissions(permissions, CAMERA_PERMISSION_REQUEST_CODE)
        }
else {
    captureAndDisplayImage()
}
```
