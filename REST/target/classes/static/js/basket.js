var counter = 1;

function addProductBox(id, name, price, owner, buyer, imageUrl) {
  var quantity = 1;
  var totalPrice = price;

var productHTML = `
<div class="cart-item">
  <img src="${imageUrl}" />
  <div class="product-details">
    <div class="top-row">
      <h4>${name}</h4>
      <p class="price">$<span id="price-${counter}" th:text="${totalPrice.toFixed(2)}">{price} </span></p>
    </div>
    <div class="bottom-row">
      <h5 style="color:Tomato;" class="last-bidder">Właściciel: ${owner}</h5>
      <h5 style="color:Tomato;" class="last-bidder">Ostatni licytujący: ${buyer}</h5>
      <div class="quantity">
 <form action="/updateItem" method="post">
       <input type="hidden" name="id" value="${id}">


        </form>
      </div>
    </div>
  </div>
</div>
`;

  document.getElementById("someElementId2").innerHTML += productHTML;

    var priceElement = document.getElementById(`price-${counter}`);
    priceElement.textContent = price.toFixed(2);
  counter++;

  calculateTotal();
}

function addProductBox2(id, name, price, owner, buyer, imageUrl) {
  var quantity = 1;
  var totalPrice = price;

var productHTML = `
<div class="cart-item">
  <img src="${imageUrl}" />
  <div class="product-details">
    <div class="top-row">
      <h4>${name}</h4>
      <p class="price">$<span id="price-${counter}" th:text="${totalPrice.toFixed(2)}">{price} </span></p>
    </div>
    <div class="bottom-row">
      <h5 style="color:Tomato;" class="last-bidder">Właściciel: ${owner}</h5>
      <h5 style="color:Tomato;" class="last-bidder">Ostatni licytujący: ${buyer}</h5>
      <div class="quantity">
 <form action="/updateItem" method="post">
       <input type="hidden" name="id" value="${id}">

        </form>
      </div>
    </div>
  </div>
</div>
`;

  document.getElementById("someElementId1").innerHTML += productHTML;

    var priceElement = document.getElementById(`price-${counter}`);
    priceElement.textContent = price.toFixed(2);
  counter++;

  calculateTotal();
}

function addProductBox3(id, name, price, owner, buyer, imageUrl) {
  var quantity = 1;
  var totalPrice = price;

var productHTML = `
<div class="cart-item">
  <img src="${imageUrl}" />
  <div class="product-details">
    <div class="top-row">
      <h4>${name}</h4>
      <p class="price">$<span id="price-${counter}" th:text="${totalPrice.toFixed(2)}">{price} </span></p>
    </div>
    <div class="bottom-row">
      <h5 style="color:Tomato;" style="color:Tomato;" class="last-bidder">Właściciel: ${owner}</h5>
      <h5 style="color:Tomato;" class="last-bidder">Ostatni licytujący: ${buyer}</h5>
      <div class="quantity">
      <form action="/updateItem" method="post">
       <input type="hidden" name="id" value="${id}">
          <label for="sum">Suma:</label>
          <input type="text" name="sum" id="sum">
        <input type="submit" value="Przebijam">
        </form>
      </div>
    </div>
  </div>
</div>

`;

  document.getElementById("someElementId").innerHTML += productHTML;

    var priceElement = document.getElementById(`price-${counter}`);
    priceElement.textContent = price.toFixed(2);
  counter++;

  calculateTotal();
}

function updateQuantity(counter, change, pricePerItem) {
  var quantityInput = document.getElementById(`quantity-${counter}`);
  var priceElement = document.getElementById(`price-${counter}`);

  var quantity = parseInt(quantityInput.value);
  var totalPrice = parseFloat(priceElement.textContent);

  quantity += change;
  totalPrice = quantity * pricePerItem;

  quantityInput.value = quantity;
  priceElement.textContent = totalPrice.toFixed(2);

  calculateTotal();
}

function removeProduct(button, counter) {
  var cartItem = button.parentNode;
  cartItem.remove();

  calculateTotal();
}

function calculateTotal() {
  var cartItems = document.querySelectorAll("#someElementId1 .cart-item");
  var totalValue = 0;

  for (var i = 0; i < cartItems.length; i++) {
    var priceElement = cartItems[i].querySelector(".price span");
    var price = parseFloat(priceElement.textContent);
    totalValue += price;
  }

  var totalValueElement = document.getElementById("total-value");
  totalValueElement.textContent = totalValue.toFixed(2);

  var deliveryCost = 20.00;
  var totalSum = totalValue + deliveryCost;
  var totalSumElement = document.getElementById("total-sum");
  totalSumElement.textContent = totalSum.toFixed(2);
}

// Wywołaj funkcję calculateTotal przy wczytaniu strony, aby początkowo obliczyć sumę
window.addEventListener("load", calculateTotal);
