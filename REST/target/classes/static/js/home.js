function addProductBox(id, name, price, imageUrl) {
  var productHTML = `
<div class="box">
  <a id="${id}" href="pages/produkt.html" onclick="storeItemSelected(this.id)">
    <div id="${id}Img">
      <img src="${imageUrl}" style="max-width: 250px">
    </div>
  </a>
  <p>${name}</p>
  <p><b>${price} zł.</b></p>
  <div class="razem">
    <form action="/updateItem" method="post">
      <input type="hidden" name="id" value="${id}">

      <label for="sum">Suma:</label>
      <input type="text" name="sum" id="sum">
      <br>

      <input type="submit" value="Przebij">
    </form>
  </div>
</div>

  `;
  document.getElementById("someElementId").innerHTML += productHTML;
}
function showPanel() {
  var panel = document.getElementById('panel');
  if (!panel) {
    // Tworzenie nowego panelu
    panel = document.createElement('div');
    panel.id = 'panel';
    panel.className = 'panel';

    // Dodawanie zawartości panelu
    panel.innerHTML = `
      <h3>Tytuł panelu</h3>
      <p>Treść panelu Lorem ipsum dolor sit amet.</p>
      <span class="close-btn">&times;</span>
    `;

    // Dodawanie panelu do kontenera
    var panelContainer = document.getElementById('panelContainer');
    panelContainer.appendChild(panel);

    // Obsługa zdarzenia dla przycisku zamknięcia
    var closeBtn = panel.querySelector('.close-btn');
    closeBtn.addEventListener('click', hidePanel);
  }

  // Wyświetlanie panelu
  panel.style.display = 'block';
}

function hidePanel() {
  var panel = document.getElementById('panel');
  if (panel) {
    // Ukrywanie panelu
    panel.style.display = 'none';
  }
}

// Pobieranie elementu zdjęcia
var image = document.getElementById('myImage');

// Dodawanie nasłuchiwacza zdarzeń dla kliknięcia w zdjęcie
image.addEventListener('click', showPanel);