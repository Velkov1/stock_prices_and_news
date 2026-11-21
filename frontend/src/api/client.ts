import type { ErrorResponse } from './types'

const BASE_URL = import.meta.env.VITE_API_BASE_URL ?? ''

export class ApiError extends Error {
  status: number

  constructor(status: number, message: string) {
    super(message)
    this.status = status
  }
}

export async function apiGet<T>(path: string, params?: Record<string, string | string[]>): Promise<T> {
  const url = new URL(BASE_URL + path, window.location.origin)

  if (params) {
    for (const [key, value] of Object.entries(params)) {
      if (Array.isArray(value)) {
        value.forEach((v) => url.searchParams.append(key, v))
      } else {
        url.searchParams.set(key, value)
      }
    }
  }

  const response = await fetch(url)

  if (!response.ok) {
    let message = response.statusText
    try {
      const body = (await response.json()) as ErrorResponse
      message = body.message ?? message
    } catch {
      // response had no JSON body, fall back to statusText
    }
    throw new ApiError(response.status, message)
  }

  return (await response.json()) as T
}
