import React, { useState, useEffect } from 'react';

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
    <>
      {products.map(product =>
        <div key={product.id}>
          [{product.maker}]
          {product.name}
          /
          {product.price}원
        </div>
      )}
    </>
  );
}
