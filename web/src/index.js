import axios from 'axios';

async function loadMessage() {
  const url = 'http://localhost:8080/hello';
  const { data } = await axios.get(url);
  const { name, message } = data;
  const element = document.getElementById('app');
  element.innerHTML = `
    <p>이름: ${name}</p>
    <p>메시지: ${message}</p>
  `;
}

loadMessage();
