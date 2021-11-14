import { useState, useEffect } from 'react';

import axios from 'axios';

async function loadProducts({ setProducts }) {
  const url = 'http://localhost:8080/products';
  const { data: products } = await axios.get(url);
  setProducts(products);
}

export default function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    loadProducts({ setProducts });
  }, []);

  return (
    <div className="products">
      {products.map(product =>
        <div className="product" key={product.id}>
          <img src={product.imageUrl} alt="제품 이미지" />
          [{product.maker}]
          {product.name}
          /
          {product.price}원
        </div>
      )}
    </div>
  );
}
