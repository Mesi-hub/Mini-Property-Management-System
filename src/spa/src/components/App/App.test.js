import { render, screen } from '@testing-library/react';
import App from '.';

import {setupStore} from "../../store"
import { renderWithProviders } from '../../util/testUtil';

test('renders Student Project text', () => {
  const store = setupStore({});
  renderWithProviders(<App />, {store});
  const linkElement = screen.getByText(/Student Project/i);
  expect(linkElement).toBeInTheDocument();
});
