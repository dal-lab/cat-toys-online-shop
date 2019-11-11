import axios from 'axios';

async function main() {
  const { data: products } = await axios.get('http://localhost:8080/products');
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

main();
