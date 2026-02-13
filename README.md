<h1 align="center">spring-qrcode-generator</h1>

<p align="center">
  <img width="220" height="220" alt="QR Code Generator" src="https://github.com/user-attachments/assets/5f1c0757-fcf1-4f72-b3bd-d3d079e65dcb" />
</p>

<p align="center">
Transforma qualquer URL em um QR Code pronto para compartilhamento rápido.
</p>

---

## <p align="center">📌 Sobre</p>

<p align="center">
API desenvolvida em Spring Boot que recebe uma URL, gera um QR Code utilizando ZXing
e armazena a imagem em um Blob Storage na Azure para acesso rápido via link.
</p>

---

## <p align="center">⚙️ Funcionamento</p>

<p align="center">
Cliente envia uma URL → API gera o QR Code → imagem é salva na Azure → link público é retornado.
</p>

<p align="center"><strong>Fluxo técnico</strong></p>

<p align="center">
<strong>1.</strong> Requisição <strong>POST</strong> para <code>/qrcode</code><br>
Body: { "text": "https://your-url.com" }
</p>

<p align="center">
<strong>2.</strong> A API gera o QR Code a partir da URL e armazena a imagem no storage
</p>

<p align="center">
<strong>3.</strong> Resposta da API:<br>
{ "url": "https://qrcode-storager.../image-id" }
</p>

<p align="center">
<strong>4.</strong> Ao acessar a URL retornada (requisição <strong>GET</strong> via navegador ou client HTTP),<br>
a imagem do QR Code é retornada para visualização.
</p>


---

## <p align="center">🛠️ Stack</p>

<p align="center">
Java • Spring Boot • ZXing • Azure Blob Storage • Docker
</p>

---

## <p align="center">🧱 Arquitetura</p>

<p align="center">
controller • dto/qrcode • service • ports • infrastructure
</p>

---

## <p align="center">💡 Motivação</p>

<p align="center">
Criado para facilitar o compartilhamento rápido de links e redes sociais
durante eventos e situações de networking.
</p>

---

<p align="center">
  <em>🔗 Uma solução prática para transformar URLs em QR Codes e facilitar o compartilhamento rápido de links.</em>
</p>
