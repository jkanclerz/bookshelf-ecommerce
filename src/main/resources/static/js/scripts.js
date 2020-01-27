async function getProducts() {
    return fetch('/api/products')
      .then((response) => response.json())
      .catch((error) => []);
}

function createProductComponent(product) {
    const template = `
      <li class="product-highlight">
        <span class="product-highlight__name">${product.name}</span>
        <div class="product-highlight__image-container">
            <img class="product-highlight__image" height=250 src="${product.cover}"/>
        </div>
        <p class="product-highlight__description">${product.description}</p>
        <span class="product-highlight__price">${product.price}</span>
        <button data-product-id="${product.id}">Add to basket</button>
      </li>
    `;

    return createHtmlElOfString(template);
}

function createHtmlElOfString(str) {
    let parent = document.createElement("div");
    parent.innerHTML = str.trim();

    return parent.firstChild;
}

function initializeBasket(quantity, total) {
    let template = `
        <div class="basket">
            <span class="basket__productsQuantity">
                products quantity:
                <span class="quantity">${quantity}</span>
            </span>
            <span class="basket__total">
                total:
                <span class="total">${total}</span>
            </span>
        </div>
    `;

    return createHtmlElOfString(template);
}

function handleAddToBasket(productId) {
    return new Promise((resolve, reject) => {

        console.log(`Going to add product with id ${productId}`);
        setTimeout(() => {
            resolve("OK")
        }, 1000);
    })
}

function initializeAddToBasketHandler(productEl) {
    productEl.querySelector('button').addEventListener('click', (e) => {
        const productId = e.target.getAttribute('data-product-id');

        handleAddToBasket(productId)
            .then(() => getCurrentOffer())
            .then(() => {
                console.log('added');
            })
            .catch((e) => {
            })
    });

    return productEl;
}

function getCurrentOffer() {
    return fetch('/api/offer')
      .then((response) => response.json())
}

(async () => {
    const productsListEl = document.querySelector('.products__highlights');
    let products = await getProducts();
    products
    .map(p => createProductComponent(p))
    .map(productEl => initializeAddToBasketHandler(productEl))
    .forEach((productEl) => {
        productsListEl.appendChild(productEl);
    })

    const basketContainerEl = document.querySelector('.basket__container');

    getCurrentOffer()
        .then(offer => {
            basketContainerEl.innerHTML = '';
            basketContainerEl.appendChild(initializeBasket(10, 20));
        });

})();
