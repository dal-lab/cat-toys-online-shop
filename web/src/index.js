import axios from 'axios';

async function loadMessage() {
  const url = 'http://localhost:8080/products';
  const { data: products } = await axios.get(url);
  const element = document.getElementById('app');
  element.innerHTML = `
    ${products.map(product => `
      <div>
        [${product.maker}]
        ${product.name}
        /
        ${product.price}원
      </div>
    `).join('')}
  `;
}

loadMessage();
