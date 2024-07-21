import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import '@testing-library/jest-dom';
import { render } from '@testing-library/react';
import { describe, expect, test, vi } from 'vitest';
import Home from "./Home";
import TProduct from './models/TProduct';
import TDataResponse from './models/data-response';

vi.mock('axios');
const axiosMock = axios as jest.Mocked<typeof axios>;

describe('App', () => {
  const products: TProduct[] = [
    { id: '1', name: 'product 1', price: 123.4, description: 'Description of the product', image: 'https://www.test.com/1.png' },
    { id: '2', name: 'product 2', price: 5678.9, description: 'Description of the product', image: 'https://www.test.com/2.png' },
    { id: '3', name: 'product 3', price: 2567.45, description: 'Description of the product', image: 'https://www.test.com/3.png' },
  ]
  test('it should render Home', async () => {

    const axiosResponse: AxiosResponse<TDataResponse<TProduct[]>> = {
      data: {
        data: products
      },
      status: 200,
      statusText: 'OK',
      headers: {},
      config: {} as InternalAxiosRequestConfig
    };
    axiosMock.get.mockResolvedValueOnce(axiosResponse);

    const { queryAllByTestId } = render(<Home />)

    const cards: HTMLElement[] = queryAllByTestId("product-card");

    expect(cards.length === products.length)
     
  });
});
