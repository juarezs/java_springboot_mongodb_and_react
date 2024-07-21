import axios from "axios";
import { useEffect, useState } from "react"
import ProductCard from "./components/ProductCard";
import TProduct from "./models/TProduct";

export default function Home() {
  const [data, setData] = useState<TProduct[]>([]);

  const loadData = async () => {
    const response = await axios.get('/api/product');
    setData(response.data);
  };

  useEffect(() => {
    loadData();

  }, []);

  const handleDelete = async (id: string) => {
    await axios.delete(`/api/product/${id}`);
    loadData();
  }

  return (
    <div className="w-full p-8">
      {data.length && (
        <div className="grid grid-cols-3 gap-8">
          {data.map((product) => (
            <ProductCard product={product} onDelete={handleDelete} />
          ))}
        </div>
      )}
    </div>
  )
}
